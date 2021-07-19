package io.v6d.core.client;

import java.io.File;

/**
 * Hello world!
 *
 */
public abstract class IPCClient extends Client
{
//    private UnixSocketChannel channel_;

    public IPCClient() {
        this.connect(System.getenv("VINEYARD_IPC_SOCKET"));
    }

    public IPCClient(String ipc_socket) {
        this.connect(ipc_socket);
    }

    private synchronized void connect(String ipc_socket) {
        File fp = new File(ipc_socket);
    }
}
