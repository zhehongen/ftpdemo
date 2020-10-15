BEGIN;
REPLACE INTO `ftp_user` (`userid`, `userpassword`, `homedirectory`, `enableflag`, `writepermission`, `idletime`, `uploadrate`, `downloadrate`, `maxloginnumber`, `maxloginperip`, `create_time`, `creator`, `update_time`, `updater`) VALUES ('admin', '123456', '/', 1, 1, 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL);
COMMIT;