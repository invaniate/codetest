package com.pierceecom.general.contracts;

import com.pierceecom.entities.PostEntity;
import com.pierceecom.general.exceptions.PierceNoResultException;

import javax.ejb.Local;
import java.util.List;

/*********************
 * Created by Alex S
 * on 17.09.2017
 ********************/

@Local
public interface PostService {

    List<PostEntity> list();
    PostEntity single(Long postId) throws PierceNoResultException;
    PostEntity create(PostEntity newPost);
    PostEntity delete(Long postId) throws PierceNoResultException;

}
