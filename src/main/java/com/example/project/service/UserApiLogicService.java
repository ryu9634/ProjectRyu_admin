package com.example.project.service;

import com.example.project.model.entity.User;
import com.example.project.model.network.Header;
import com.example.project.model.network.Pagination;
import com.example.project.model.network.request.UserApiRequest;
import com.example.project.model.network.response.UserApiResponse;
import com.example.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {

    @Autowired
    private UserRepository userRepository;

    //회원가입
    @Transactional
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();
        User user = User.builder()
                .userUserid(userApiRequest.getUserUserid())
                .userUserpw(userApiRequest.getUserUserpw())
                .userName(userApiRequest.getUserName())
                .userHp(userApiRequest.getUserHp())
                .userEmail(userApiRequest.getUserEmail())
                .userSns(userApiRequest.getUserSns())
                .userZipcode(userApiRequest.getUserZipcode())
                .userAddress1(userApiRequest.getUserAddress1())
                .userAddress2(userApiRequest.getUserAddress2())
                .build();
        User newUser = baseRepository.save(user);
        return Header.OK(response(newUser));
    }
    @Transactional
    //아이디 출력
    @Override
    public Header<UserApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(user -> response(user))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }
    @Transactional
    //회원정보수정
    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();
        Optional<User> user = userRepository.findByUserIdx(userApiRequest.getUserIdx());

        return user.map(users -> {
                    users.setUserUserid(userApiRequest.getUserUserid());
                    users.setUserUserpw(userApiRequest.getUserUserpw());
                    users.setUserHp(userApiRequest.getUserHp());
                    users.setUserEmail(userApiRequest.getUserEmail());
                    users.setUserName(userApiRequest.getUserName());
                    users.setUserSns(userApiRequest.getUserSns());
                    users.setUserZipcode(userApiRequest.getUserZipcode());
                    users.setUserAddress1(userApiRequest.getUserAddress1());
                    users.setUserAddress2(userApiRequest.getUserAddress2());
                    return users;
                }).map(users -> baseRepository.save(users))
                .map(users -> response(users))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }
    @Transactional
    //회원탈퇴
    @Override
    public Header delete(Long id) {
        Optional<User> user = baseRepository.findById(id);

        return user.map(users -> {
            baseRepository.delete(users);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public UserApiResponse response(User user){
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .userIdx(user.getUserIdx())
                .userUserid(user.getUserUserid())
                .userUserpw(user.getUserUserpw())
                .userEmail(user.getUserEmail())
                .userHp(user.getUserHp())
                .userRegdate(user.getUserRegdate())
                .userName(user.getUserName())
                .userSns(user.getUserSns())
                .userZipcode(user.getUserZipcode())
                .userAddress1(user.getUserAddress1())
                .userAddress2(user.getUserAddress2())
                .build();
        return userApiResponse;
    }
    @Transactional
    public Header<List<UserApiResponse>> search(Pageable pageable){
        Page<User> users = baseRepository.findAll(pageable);
        List<UserApiResponse> userApiResponseList = users.stream()
                .map(user -> response(user))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElements(users.getNumberOfElements())
                .build();
        return Header.OK(userApiResponseList, pagination);
    }

    @Transactional
    public Header<List<UserApiResponse>> list() {
        List<User> users = baseRepository.findAll();
        List<UserApiResponse> userApiResponseList = users.stream()
                .map(user -> response(user))
                .collect(Collectors.toList());
        return Header.OK(userApiResponseList);
    }

    public List<UserApiResponse> getUserList(){
        List<User> userList = baseRepository.findAll();
        List<UserApiResponse> userApiResponseList  = userList.stream()
                .map(user -> response(user))
                .collect(Collectors.toList());
        return userApiResponseList;
    }

}
