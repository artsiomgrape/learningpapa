package by.mogilev.learning;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    List<Artist> artists;


    public HelloController(List<Artist> artists) {
        artists.add(new Artist("Name1"));
        artists.add(new Artist("Name2"));
        artists.add(new Artist("Name3"));
        this.artists = artists;
    }

    @GetMapping("/")
    public String index() {
        return "Привет ВСЕМ!!!";
    }

    @GetMapping("/artists")
    public List<Artist> getArtists() {
        return artists;
    }

    @PostMapping("/artists")
    public void createArtist(@RequestBody Artist artist) {
        artists.add(artist);
    }

    @PutMapping("/artists/{name}")
    public void updateArtist(@PathVariable("name") String name,
                             @RequestBody Artist artist) {

        Artist findArtist = artists.stream().filter(a-> a.getName().equals(name)).findFirst().get();
        findArtist.setName(artist.getName());
    }

    @DeleteMapping("/artists/{name}")
    public void deleteArtist(@PathVariable("name") String name) {

        Artist findArtist = artists.stream().filter(a-> a.getName().equals(name)).findFirst().get();
        artists.remove(findArtist);
    }


}
