package com.ssafy.hallhole.item.service;

import com.ssafy.hallhole.advice.exceptions.NotFoundException;
import com.ssafy.hallhole.item.domain.Item;
import com.ssafy.hallhole.item.domain.ItemType;
import com.ssafy.hallhole.item.domain.OwnItem;
import com.ssafy.hallhole.item.dto.ItemChangeInputDTO;
import com.ssafy.hallhole.item.dto.ItemInputDTO;
import com.ssafy.hallhole.item.repository.ItemRepository;
import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    private final MemberRepository memberRepository;

    @Override
    public List<Item> getAllItemByType(String type) {

        return itemRepository.findAllByType(type);
    }

    @Override
    public List<OwnItem> getAllMyItem(String tag) throws NotFoundException {

        Member m = memberRepository.findByIdTag(tag);
        if(m==null || m.isOut()){
            throw new NotFoundException(" 유효한 사용자가 아닙니다.");
        }

        return itemRepository.findAllMyItem(m.getId());
    }

    @Override
    public void addItem(ItemInputDTO inputDTO) {
        Item item = new Item(inputDTO.getItemType(), inputDTO.getPrice());
        itemRepository.save(item);
    }

    @Override
    public void myItemChange(ItemChangeInputDTO inputDTO) throws NotFoundException {

        Member m = memberRepository.findByIdTag(inputDTO.getIdTag());
        if(m==null || m.isOut()){
            throw new NotFoundException("유효한 사용자가 아닙니다.");
        }

        Item acc = itemRepository.findById(inputDTO.getAcc_id()).get();
        Item character = itemRepository.findById(inputDTO.getChar_id()).get();

        if(acc==null){
            throw new NotFoundException("해당 악세사리는 존재하지 않습니다.");
        }
        if(character==null){
            throw new NotFoundException("해당 색상이 존재하지 않습니다.");
        }

        m.setNowChar(character.getId().intValue());
        m.setNowAcc(acc.getId().intValue());

        memberRepository.save(m);
    }
}
