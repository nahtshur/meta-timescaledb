LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a6919544b6c76fc2e0debe84e8dfc7a5"

SRCREV = "b064b2cd531cda8b8c4c077cc993328d6d10a211"
SRC_URI = "git://github.com/timescale/timescaledb.git;branch=2.9.x"

SRC_URI += "file://pg_config"

S = "${WORKDIR}//git"

DEPENDS += "postgresql"
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

FILES:${PN} += "${libdir}/postgresql/lib/*"
FILES:${PN} += "${datadir}"
FILES:${PN} += "${datadir}/postgresql"
FILES:${PN} += "${datadir}/postgresql/extension/*"


