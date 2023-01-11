# add a scripted version of pg_config (which is a binary) for cross-compiling
FILESEXTRAPATHS:prepend := "${THISDIR}"

SRC_URI += "file://pg-config"

RDEPENDS_${PN}-dev += "bash"
BINCONFIG = "${bindir}/pg-config"

inherit binconfig

do_install:append() {
  install -d ${D}${bindir}
  install -m 0755 ${WORKDIR}/pg-config ${D}${bindir}/pg-config
}

sysroot_stage_all:append() {
  install -d ${SYSROOT_DESTDIR}${bindir}/crossscripts
  install -m 0755 ${D}${bindir}/pg-config ${SYSROOT_DESTDIR}${bindir}/crossscripts/pg_config
}
