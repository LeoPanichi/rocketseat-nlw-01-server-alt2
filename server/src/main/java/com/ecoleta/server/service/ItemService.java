package com.ecoleta.server.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ecoleta.server.model.item.Item;
import com.ecoleta.server.model.item.ItemResponseDTO;
import com.ecoleta.server.repositories.ItemRepository;

@Service
public class ItemService {
    @Autowired
    Environment environment;

    @Autowired
    private ItemRepository itemRepository;

    public ItemResponseDTO getPointById(int id) throws UnknownHostException{
        // Port
        String port = environment.getProperty("server.port") == null ? "8080" : environment.getProperty("server.port");
        
        // Local address
        String ip = InetAddress.getLocalHost().getHostAddress();
        //String host = InetAddress.getLocalHost().getHostName();
        Item item = itemRepository.findById(id).get();
        ItemResponseDTO response = new ItemResponseDTO("http://"+ip+":"+port+"/uploads/"+item.getImage(), item.getTitle());
        return response;
    }

}
