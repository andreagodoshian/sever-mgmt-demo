package com.freyaselberg.serverdemo.service;

import com.freyaselberg.serverdemo.model.Server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

public interface ServerService {

    Server create(Server server);

    Server ping(String ipAddress) throws IOException;
    // finds server by ip && returns ping'd server

    Collection<Server> list(int limit);
    // limit, as in... five servers? ten servers?

    Server get(Long id);

    Server update(Server server);

    Boolean delete(Long id);
}
