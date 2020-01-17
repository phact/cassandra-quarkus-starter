package project.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.metadata.Node;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.net.InetSocketAddress;
import java.security.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class CassandraManager {

    private CqlSession session;
    private Logger logger = Logger.getLogger(CassandraManager.class);


    public List connect(com.datastax.powertools.cassandra.CassandraClusterConfiguration config) {
        CqlSessionBuilder builder = CqlSession.builder();
        builder = builder.addContactPoint(InetSocketAddress.createUnresolved(
                config.getContactPoints(),
                config.getCqlPort()));

        // TODO: didn't work - trying to fix native image
        // https://docs.datastax.com/en/developer/java-driver/4.2/manual/osgi/
        // https://github.com/oracle/graal/blob/master/substratevm/REFLECTION.md
        /*
        builder = builder
                .withClassLoader(CqlSession.class.getClassLoader());

         */

        if (config.getCqlUserName() != null) {
            builder = builder.withAuthCredentials(config.getCqlUserName(), config.getCqlPassword());
        }
        if (config.getLocalDC() != null) {
            builder = builder.withLocalDatacenter(config.getLocalDC());
        }
        if (config.getSslKsPass() != null && config.getSslKsPath() != null) {
            builder = builder.withSslContext(createSSLOptions(config.getSslTsPath(), config.getSslTsPass(), config.getSslKsPath(), config.getSslKsPass()));

            logger.info("Enabling SSL with sskKsPath=" + config.getSslKsPath());
            throw new RuntimeException("not implemented");
        }

        session = builder.build();

        Map<UUID, Node> nodes = session.getMetadata().getNodes();
        List cassandraNodeList = new ArrayList();
        return cassandraNodeList;
    }

    private SSLContext createSSLOptions(String truststorePath, String truststorePwd, String keystorePath, String keystorePwd) {
        try {
            TrustManagerFactory tmf = null;
        if (null != truststorePath) {
            KeyStore tks = null;
                tks = KeyStore.getInstance("JKS");
            tks.load(this.getClass().getResourceAsStream(truststorePath),
                    truststorePwd.toCharArray());
            tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(tks);
        }
        KeyManagerFactory kmf = null;
        if (null != keystorePath) {
            KeyStore kks = KeyStore.getInstance("JKS");
            kks.load(this.getClass().getResourceAsStream(keystorePath),
                    keystorePwd.toCharArray());
            kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(kks, keystorePwd.toCharArray());
        }
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf != null? kmf.getKeyManagers() : null,
                tmf != null ? tmf.getTrustManagers() : null,
                new SecureRandom());
        return sslContext;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}

