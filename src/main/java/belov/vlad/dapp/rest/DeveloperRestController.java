package belov.vlad.dapp.rest;

import belov.vlad.dapp.model.Developer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperRestController {

    private List<Developer> DEVELOPERS = Stream.of(
            new Developer(1L, "Viktor", "Petrov"),
            new Developer(2L, "Grigoriy", "Maslenkov"),
            new Developer(3L, "Яна", "Герасимова")
            ).collect(Collectors.toList());

    @GetMapping
    public List<Developer> getAll(){
        return DEVELOPERS;
    }
    @GetMapping("/{id}")
    public Developer getById(@PathVariable Long id){
        Developer d = DEVELOPERS.stream().filter(developer -> developer.getId().equals(id)).findFirst().orElse(null);
        System.out.println(d);
        return d;
    }
}
