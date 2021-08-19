package com.gutmox.ioc;

import com.gutmox.handlers.AccountsHandler;
import com.gutmox.handlers.HealthHandler;
import com.gutmox.handlers.HelloHandler;
import com.gutmox.router.Routing;

public class IoC {

    private final Routing routing;
    private final HelloHandler helloHandler;
    private final HealthHandler healthCheck;
    private final AccountsHandler accountsHandler;
    private static IoC instance = null;

    public static synchronized IoC getInstance() {
        if (instance == null) {
            instance = new IoC();
        }
        return instance;
    }

    public IoC() {
        routing = new Routing();
        healthCheck = new HealthHandler();
        helloHandler = new HelloHandler();
        accountsHandler = new AccountsHandler();
    }

    public Routing getRouting() {
        return routing;
    }

    public HelloHandler getHelloHandler() {
        return helloHandler;
    }

    public HealthHandler getHealthCheck() {
        return healthCheck;
    }

    public AccountsHandler getAccountsHandler() {
        return accountsHandler;
    }
}
