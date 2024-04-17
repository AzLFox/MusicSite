package ru.azlfox.musicsite.service;

import ru.azlfox.musicsite.entity.Composition;
import ru.azlfox.musicsite.entity.Like;
import ru.azlfox.musicsite.entity.User;
import ru.azlfox.musicsite.exception.NoDataException;
import ru.azlfox.musicsite.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LikeService {
    private final LikeRepository likeRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository){
        this.likeRepository = likeRepository;
    }

    public Like getLikeById(Long id){
        return likeRepository.getLikeById(id).orElseThrow(()-> new NoDataException("Like with id = " + id + "not found !"));
    }

    public List<Like> getAllByAuthor(User userId){
        return likeRepository.getAllByUserId(userId).orElseThrow(()-> new NoDataException("not found !"));
    }

    public List<Like> getAllByComposition(Composition compositionTitle){
        return likeRepository.getAllByWorkId(compositionTitle).orElseThrow(()-> new NoDataException("not found !"));
    }

    public void addLike(Like like) {
        likeRepository.save(like);
    }

    public void deleteLikeById(Long id){
        likeRepository.deleteAllById(id);
    }
    public void deleteLikeByUserId(User userId){likeRepository.deleteLikesByUserId(userId);}
}
