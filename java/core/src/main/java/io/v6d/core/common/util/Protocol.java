package io.v6d.core.common.util;

public class Protocol {
    public abstract static class Reply {
    }

    public class RegisterReply extends Reply {
        public String ipc_socket;
        public String rpc_endpoint;
        public InstanceID instance_id;
    }

    public class Register {
        public static
    }
}
