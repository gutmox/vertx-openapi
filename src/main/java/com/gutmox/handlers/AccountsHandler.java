package com.gutmox.handlers;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.RoutingContext;

public class AccountsHandler {

    private final JsonArray accounts = new JsonArray();

    public AccountsHandler() {
        initAccounts();
    }

    private void initAccounts() {
        JsonObject account = new JsonObject();
        account.put("number", "0182021901202184");
        accounts.add(account);
    }

    public void execute(RoutingContext context) {
        context.response().putHeader("content-type", "application/json")
                .end(accounts.encode());
    }
}
