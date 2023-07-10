LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a6919544b6c76fc2e0debe84e8dfc7a5"

SRCREV = "01c4a2c2ae16cd507e79802f39f9709d38f11d9e"
SRC_URI = "git://github.com/timescale/timescaledb.git;branch=2.10.x"

SRC_URI += "file://pg_config"

S = "${WORKDIR}//git"

DEPENDS += "postgresql"
RDEPENDS:${PN} += "postgresql"
inherit cmake

# We rely on postgresql to have a crossscript alternative to the binary pg_config
#  --with-pgconfig=${STAGING_BINDIR_CROSS}/pg_config 
EXTRA_OECONF = "\
  --with-pgconfig=${D}/pg_config \
  --with-gdallibdir=${STAGING_LIBDIR} \
"

do_configure:prepend() {
echo "Configuring"
# chmod 755 ${WORKDIR}/pg_config
install -m 755 ${WORKDIR}/pg_config ${STAGING_DIR_TARGET}/pg_config
echo ${STAGING_DIR_TARGET}/pg_config
}

do_configure:append() {
rm ${STAGING_DIR_TARGET}/pg_config
}

FILES:${PN} += "*"

FILES:${PN} += "${libdir}/postgresql"
FILES:${PN} += "${datadir}/postgresql/extension/*"


