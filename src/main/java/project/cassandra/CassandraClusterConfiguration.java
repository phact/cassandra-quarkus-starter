package com.datastax.powertools.cassandra;

public class CassandraClusterConfiguration {

    private int cqlPort = 9042;
    private String contactPoints;
    private String localDC;
    private String cqlUserName;
    private String cqlPassword;
    private String sslKsPath;
    private String sslKsPass;
    private String sslTsPath;
    private String sslTsPass;
    private String sshUser;
    private String privateKey;

    public int getCqlPort() {
        return cqlPort;
    }

    public void setCqlPort(int cqlPort) {
        this.cqlPort = cqlPort;
    }

    public String getContactPoints() {
        return contactPoints;
    }

    public void setContactPoints(String contactPoints) {
        this.contactPoints = contactPoints;
    }

    public String getLocalDC() {
        return localDC;
    }

    public void setLocalDC(String localDC) {
        this.localDC = localDC;
    }

    public String getCqlUserName() {
        return cqlUserName;
    }

    public void setCqlUserName(String cqlUserName) {
        this.cqlUserName = cqlUserName;
    }

    public String getCqlPassword() {
        return cqlPassword;
    }

    public void setCqlPassword(String cqlPassword) {
        this.cqlPassword = cqlPassword;
    }

    public String getSslKsPath() {
        return sslKsPath;
    }

    public void setSslKsPath(String sslKsPath) {
        this.sslKsPath = sslKsPath;
    }

    public String getSslKsPass() {
        return sslKsPass;
    }

    public void setSslKsPass(String sslKsPass) {
        this.sslKsPass = sslKsPass;
    }

    public String getSslTsPath() {
        return this.sslTsPath;
    }

    public String getSslTsPass() {
        return sslTsPass;
    }

    public void setSslTsPass(String sslTsPass) {
        this.sslTsPass = sslTsPass;
    }

    public void setSslTsPath(String sslTsPath) {
        this.sslTsPath = sslTsPath;
    }

    public String getSshUser() {
        return sshUser;
    }

    public void setSshUser(String sshUser) {
        this.sshUser = sshUser;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}

