package com.ecoleta.server.model.point;


public record PointResponseDTO(
                            String imageName, 
                            String name, 
                            String email, 
                            String whatsapp, 
                            double latitude, 
                            double longitude, 
                            String city, 
                            String uf
) {

}
