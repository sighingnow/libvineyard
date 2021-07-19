package io.v6d.core.client;

import io.v6d.core.ObjectMeta;
import io.v6d.core.common.util.ObjectID;

/**
 * Hello world!
 *
 */
public abstract class Client
{
    protected String ipc_socket;
    protected String rpc_endpoint;

    public abstract ObjectID CreateMetaData(ObjectMeta metadata);

    public ObjectMeta GetMetaData(ObjectID id) {
        return this.GetMetaData(id, false);
    }

    public abstract ObjectMeta GetMetaData(ObjectID id, boolean sync_remote);

    public boolean Connected() {
        return false;
    }

    public void Disconnect() {
    }

    public String IPCSocket() {
        return ipc_socket;
    }

    public String RPCEndpoint() {
        return rpc_endpoint;
    }
}
