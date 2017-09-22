package com.pierceecom.services;

import com.pierceecom.dao.MockedDAO;
import com.pierceecom.entities.PostEntity;
import com.pierceecom.general.contracts.PostService;
import com.pierceecom.general.exceptions.PierceNoResultException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/*********************
 * Created by Alex S
 * on 17.09.2017
 ********************/

@Stateless
public class PostServiceImpl implements PostService {

    @Inject
    private MockedDAO<PostEntity> dao;

    public List<PostEntity> list() {
        return dao.getAll();
    }

    public PostEntity single(Long postId) throws PierceNoResultException {
        return dao.get(postId);
    }

    public PostEntity create(PostEntity newPost) {
        return dao.persist(newPost);
    }

    public PostEntity delete(Long postId) throws PierceNoResultException{
        return dao.delete(postId);
    }
}
