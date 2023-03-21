package be.ucll.gip5.service;

import be.ucll.gip5.entity.House;
import be.ucll.gip5.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailService {
    @Autowired
    private HouseRepository houseRepository;

    public List<User> getEmailsForNewHome(Long id){
        List<User> emailsToSend = new ArrayList<>();
        House house = houseRepository.findAllByHouseId(id);
        emailsToSend.add((User) house.getUserList());
        return emailsToSend;
    }

    public String mailTekst(Long id){
        House house = houseRepository.findAllByHouseId(id);

        LocalDateTime dateOfCreation = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDateOfCreation = dateOfCreation.format(dateTimeFormatter);

        String newLine = System.getProperty("line.separator");

        return "-----MATCH INFO-----"
                .concat(newLine)
                .concat("New house created on "+formattedDateOfCreation)
                .concat(newLine)
                .concat(newLine)
                .concat("HouseID: "+house.getHouseId())
                .concat("Name: "+house.getName())
                .concat("Address: "+house.getAddress())
                .concat(newLine)
                .concat("Members of this house: "+house.getUserList())
                .concat(newLine)
                .concat("List of spaces: "+house.getSpaceList())
                .concat(newLine)
                .concat(newLine)
                .concat("A lot of success by using your new Smart Home Control!");
    }
}