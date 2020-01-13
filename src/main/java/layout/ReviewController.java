package layout;

import logic.ReviewService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {

    private ReviewService reviewService;
}

//
//package demo;
//
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.http.MediaType;
//        import org.springframework.web.bind.annotation.PathVariable;
//        import org.springframework.web.bind.annotation.RequestBody;
//        import org.springframework.web.bind.annotation.RequestMapping;
//        import org.springframework.web.bind.annotation.RequestMethod;
//        import org.springframework.web.bind.annotation.RestController;
//
//        import reactor.core.publisher.Flux;
//        import reactor.core.publisher.Mono;
//
//@RestController
//public class ReactiveTeamsController {
//    private ReactiveTeamsService teams;
//
//    @Autowired
//    public ReactiveTeamsController(ReactiveTeamsService teams) {
//        super();
//        this.teams = teams;
//    }
//
//    @RequestMapping(path = "/teams", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<Team> create (@RequestBody Team team){
//        return this.teams
//                .create(team);
//    }
//
//    @RequestMapping(path = "/teams/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<Team> getTeamById(@PathVariable("id") String id){
//        return this.teams
//                .getTeamById(id);
//    }
//
//    @RequestMapping(path = "/teams", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<Team> getAllTeams(){
//        return this.teams
//                .getAllTeams();
//    }
//
//    @RequestMapping(path = "/teams", method = RequestMethod.DELETE)
//    public Mono<Void> cleanup(){
//        return this.teams
//                .cleanup();
//    }
//
//}












