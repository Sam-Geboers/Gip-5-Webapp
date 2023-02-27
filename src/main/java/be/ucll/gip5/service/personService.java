package be.ucll.gip5.service;

import be.ucll.gip5.dto.PersonDTO;
import be.ucll.gip5.entity.Person;
import be.ucll.gip5.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class personService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private DTOConverter dtoConverter;

    public void addPerson(PersonDTO dto){
        Person person = dtoConverter.PersonDTOToEntity(dto);
        personRepository.save(person);
    }
    public void editPerson(PersonDTO dto, Long id) throws Exception{
        Person person = personRepository.findAllByPersonId(id);
        if (person != null){
            person = dtoConverter.PersonEntityToEntity(person,dto);
            personRepository.save(person);
        }else {
            throw new ClassNotFoundException("Person not Found");
        }
    }
    public void deletePersonById(Long id) throws Exception{
        Person person = personRepository.findAllByPersonId(id);
        if (person != null){
            personRepository.delete(person);
        }else {
            throw new ClassNotFoundException("Person not Found");
        }
    }
    public List<PersonDTO> getAllPerson(){
        List<Person> personList = personRepository.findAll();
        List<PersonDTO> personDTOS = new ArrayList<>();
        for(Person p: personList){
            personDTOS.add(dtoConverter.PersonEntityToDTO(p));
        }
        return personDTOS;
    }
    public PersonDTO getPersonById(Long id) throws Exception{
        Person person = personRepository.findAllByPersonId(id);
        if (person != null){
            return dtoConverter.PersonEntityToDTO(person);
        }else {
            throw new ClassNotFoundException("Person not found");
        }
    }
}
