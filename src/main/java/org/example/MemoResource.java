package org.example;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import java.util.List;

/**
 *CRUD Übungen
 * Create = @post und mit persist Methode. INSERT INTO memo (id, content) VALUES (?, ?);
 * Read = @get
 * Update =
 * Delete =
 *
 * @Path ist Webseite path wie www.example/memos
 **/

@Path("/memos")
public class MemoResource {

    @GET
    @Transactional
    public List<Memo> getAllMemos() {
        return Memo.listAll();
    }

    @POST
    @Transactional
    public Memo createMemo(Memo memo){
        memo.persist();
        return memo;
    }


}
