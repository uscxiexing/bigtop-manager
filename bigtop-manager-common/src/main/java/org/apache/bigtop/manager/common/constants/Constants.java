package org.apache.bigtop.manager.common.constants;

public final class Constants {

    private Constants() {
        throw new UnsupportedOperationException("Construct Constants");
    }

    /**
     * stack cache dir
     */
    public static final String STACK_CACHE_DIR = "/opt/bigtop-manager-agent/cache";

    /**
     * host key for all hosts
     */
    public static final String ALL_HOST_KEY = "all";

    /**
     * registry session timeout
     */
    public static final long REGISTRY_SESSION_TIMEOUT = 5 * 1000L;
}
