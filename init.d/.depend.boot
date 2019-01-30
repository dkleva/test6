TARGETS = console-setup resolvconf mountkernfs.sh ufw hostname.sh screen-cleanup apparmor plymouth-log x11-common udev keyboard-setup cryptdisks cryptdisks-early hwclock.sh urandom networking mountdevsubfs.sh checkroot.sh open-iscsi iscsid lvm2 checkfs.sh mountall.sh checkroot-bootclean.sh kmod procps mountall-bootclean.sh bootmisc.sh mountnfs-bootclean.sh mountnfs.sh
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
hwclock.sh: mountdevsubfs.sh
urandom: hwclock.sh
networking: resolvconf mountkernfs.sh urandom procps
mountdevsubfs.sh: mountkernfs.sh udev
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh keyboard-setup
open-iscsi: networking iscsid
iscsid: networking
lvm2: cryptdisks-early mountdevsubfs.sh udev
checkfs.sh: cryptdisks lvm2 checkroot.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh lvm2
checkroot-bootclean.sh: checkroot.sh
kmod: checkroot.sh
procps: mountkernfs.sh udev
mountall-bootclean.sh: mountall.sh
bootmisc.sh: mountall-bootclean.sh udev mountnfs-bootclean.sh checkroot-bootclean.sh
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking
