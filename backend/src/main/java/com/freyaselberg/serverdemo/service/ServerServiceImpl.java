package com.freyaselberg.serverdemo.service;

import com.freyaselberg.serverdemo.model.Server;
import com.freyaselberg.serverdemo.model.Status;
import com.freyaselberg.serverdemo.repo.ServerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService{

    private final ServerRepo serverRepo;
    // constructor injection via lombok annotation

    @Override
    public Server create(Server server) {
        log.info("Attempting to save new server: {}", server.getName());
        server.setImgUrl(setServerImgUrl()); // at bottom
        return serverRepo.save(server);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Attempting to ping server IP: {}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Attempting to fetch {} server(s)", limit);
        return serverRepo.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Attempting to fetch server id: {}", id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Attempting to update server: {}", server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Attempting to delete id: {}", id);
        serverRepo.deleteById(id);
        return true;
    }

    private String setServerImgUrl() {
        String[] imageNames = {"basicServer.jpg",
        "basicServer2.png", "basicServer3.png",
        "basicServer4.png"};

        return this.getClass().getClassLoader()
                .getResource("static/" +
                        imageNames[new Random().nextInt(4)])
                .toString();
    }
}