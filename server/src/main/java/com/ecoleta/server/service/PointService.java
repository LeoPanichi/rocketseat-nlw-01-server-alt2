package com.ecoleta.server.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecoleta.server.model.item.Item;
import com.ecoleta.server.model.point.Point;
import com.ecoleta.server.model.point.PointRequestDTO;
import com.ecoleta.server.model.point.PointResponseDTO;
import com.ecoleta.server.repositories.ItemRepository;
import com.ecoleta.server.repositories.PointRepository;

import static com.ecoleta.server.repositories.specification.PointSpecification.hasItems;
import static com.ecoleta.server.repositories.specification.PointSpecification.inCity;
import static com.ecoleta.server.repositories.specification.PointSpecification.inUf;

@Service
public class PointService {
    @Autowired
    Environment environment;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Point createPoint(PointRequestDTO req) throws IOException{
        String imgName = null;
        Set<Item> itemsSet = new HashSet<Item>();

        if (req.image() != null){
            imgName = this.uploadImg(req.image());
        }

        Point newPoint = new Point();
        newPoint.setImage(imgName);
        newPoint.setName(req.name());
        newPoint.setEmail(req.email());
        newPoint.setWhatsapp(req.whatsapp());
        newPoint.setLatitude(req.latitude());
        newPoint.setLongitude(req.longitude());
        newPoint.setCity(req.city());
        newPoint.setUf(req.uf());
        for(int i=0; i<req.items().split(",").length ; i++) {
            itemsSet.add(itemRepository.findById(Integer.parseInt(req.items().split(",")[i])).get());
        }
        newPoint.setItems(itemsSet);

        pointRepository.save(newPoint);

        return newPoint;
    }
    
    public List<PointResponseDTO> getPoints(String city, String uf, String items){
        List<Integer> listItems = new ArrayList<Integer>();
        for (String item: items.split(",")){
            listItems.add(Integer.parseInt(item));
        }
        Specification<Point> filters = Specification.where(StringUtils.isBlank(city) ? null : inCity(city))
                                                    .and(StringUtils.isBlank(uf) ? null : inUf(uf))
                                                    .and(StringUtils.isBlank(items) ? null : hasItems(listItems));
        List<PointResponseDTO> response = new ArrayList<PointResponseDTO>();
        List<Point> points = pointRepository.findAll(filters);
        points.forEach(point -> {
            try {
                response.add(new PointResponseDTO(genUrl(point.getImage()), point.getName(), point.getEmail(), point.getWhatsapp(), point.getLatitude(), point.getLongitude(), point.getCity(), point.getUf()));
            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        return response;
    }

    public PointResponseDTO getPointById(int id) throws UnknownHostException{
        Point point = pointRepository.findById(id).get();
        PointResponseDTO response = new PointResponseDTO(genUrl(point.getImage()), point.getName(), point.getEmail(), point.getWhatsapp(), point.getLatitude(), point.getLongitude(), point.getCity(), point.getUf());
        return response;
    }

    private String uploadImg(MultipartFile img) throws IOException{
        String imgName = UUID.randomUUID() + img.getOriginalFilename();
        File file = new File("src/main/resources/static/uploads/user/" + imgName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(img.getBytes());
        fos.close();
        return imgName; 
    }

    private String genUrl(String imgName) throws UnknownHostException{
        // Port
        String port = environment.getProperty("server.port") == null ? "8080" : environment.getProperty("server.port");
        
        // Local address
        String ip = InetAddress.getLocalHost().getHostAddress();
        //String host = InetAddress.getLocalHost().getHostName();
        return "http://"+ip+":"+port+"/uploads/user/"+imgName;
    }

}
