package com.ecoleta.server.model.point;

import org.springframework.web.multipart.MultipartFile;

public record PointRequestDTO(
                            MultipartFile image, 
                            String name, 
                            String email, 
                            String whatsapp, 
                            double latitude, 
                            double longitude, 
                            String city, 
                            String uf, 
                            String items
                            ) {

}
