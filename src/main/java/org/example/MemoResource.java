package org.example;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.jboss.logging.Logger;

import java.util.List;


/**
 *CRUD Übungen
 * Create = @post und mit persist Methode. INSERT INTO memo (id, content) VALUES (?, ?);
 * Read = @get
 * Update =@put(alles ändern) & @patch(Teilweise ändern)
 * Delete =@delete
 *
 * @Path ist Webseite path wie www.example/memos
 **/

@Path("/memos")
public class MemoResource {

    private static final Logger LOG = Logger.getLogger(MemoResource.class);


    @POST
    @Transactional
    public Memo createMemo(Memo memo){
        memo.persist();
        return memo;
    }

    @GET
    @Transactional
    public List<Memo> getAllMemos() {
        return Memo.listAll();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Memo updateMemo(@PathParam("id") Long id, Memo memoToUpdate) {
        Memo memo = findMemoById(id);

        memo.content = memoToUpdate.content;

        LOG.infof("Successfully updated memo with id: %d", id);
        return memo;
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteMemo(@PathParam("id") Long id) {
        Memo memo = findMemoById(id);

        memo.delete();
        LOG.infof("Successfully deleted memo with id: %d", id);
        }

    private Memo findMemoById(Long id) {
        Memo memo = Memo.findById(id);
        if (memo == null) {
            throw new NotFoundException("Memo with id " + id + " does not exist.");
        }
        return memo;
    }

}
