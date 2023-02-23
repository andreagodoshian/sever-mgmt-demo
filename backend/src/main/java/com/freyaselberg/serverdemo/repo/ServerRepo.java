package com.freyaselberg.serverdemo.repo;

import com.freyaselberg.serverdemo.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository<Server, Long> {

    Server findByIpAddress(String ipAddress);

    // Server findByName(String name);
    // ^^won't work *as is* [return one Server] since not unique

}