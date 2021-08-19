package com.gutmox.router;

import com.gutmox.ioc.IoC;
import io.reactivex.Single;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import io.vertx.reactivex.ext.web.handler.StaticHandler;

public class Routing {

    private static final Logger LOGGER = LoggerFactory.getLogger(Routing.class.getName());

    private static final String HEALTH_CHECK = "/health";
    private static final String HELLO = "/hello";

    public Single<Router> createRouter() {
        return OpenAPI3RouterFactory
                .rxCreate(Vertx.currentContext().owner(), "webroot/swagger/swagger.yml")
                .doOnError(LOGGER::error)
                .map(routerFactory -> {
                    addHandlersByOperation(routerFactory);
                    return getRouter(routerFactory);
                });
    }

    private void addHandlersByOperation(OpenAPI3RouterFactory routerFactory) {
        routerFactory.addHandlerByOperationId("getAccounts", IoC.getInstance().getAccountsHandler()::execute);
    }

    private Router getRouter(OpenAPI3RouterFactory routerFactory) {
        Router router = routerFactory.getRouter();
        router.post().handler(BodyHandler.create());
        router.get(HELLO).handler(IoC.getInstance().getHelloHandler()::execute);
        router.get(HEALTH_CHECK).handler(IoC.getInstance().getHealthCheck()::execute);
        router.get("/*").handler(StaticHandler.create());
        return router;
    }
}

