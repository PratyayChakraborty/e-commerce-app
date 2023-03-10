package com.ecom.Service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.Model.Items;
import com.ecom.Repository.ItemsRepository;
@Service
public class ItemServiceImpl implements ItemsService{


    @Autowired
    ItemsRepository itemsRepository;


    @Override
    public Items getItem(Integer itemId){

        return itemsRepository.findById( itemId )
                .orElseThrow( ()-> new RuntimeException("Item doesn't exsist") );
    }

    @Override
    public List<Items> getAllItems() {
        return itemsRepository.findAll();
    }


    @Override
    public List<Items> searchItemsByName(String itemName) {
        return itemsRepository.findAllByNameContains( itemName )
                              .orElseThrow(()->
                                        new RuntimeException("Not Items found"));
    }


    @Override
    public List<Items> searchItemsByPrice(String name, Integer itemPrice) {

        return itemsRepository.findAllByNameAndPrice( name, (double)itemPrice ).orElseGet( ()-> itemsRepository
                .findAllByNameAndPriceIsBetween( name, (double)itemPrice-500 , (double)itemPrice+500)
                .orElseThrow(()-> new RuntimeException("No Items found")));
    }


    @Override
    public List<Items> searchItemsInPriceRange(String name , Integer low, Integer high) {


        return itemsRepository
                .findAllByNameAndPriceIsBetween(name , (double)low , (double)high)
                .orElseThrow(()->new RuntimeException("No Items found"));
    }


    @Override
    public List<Items> searchItemsByCategory(String type) {

        return itemsRepository.findAllByCategory( type )
                .orElseThrow(()-> new RuntimeException("No product Found with this Category"));

    }


    @Override
    public List<Items> sortItemsByPriceHighToLow(String name) {

        return itemsRepository.findAllByNameContainsOrderByPriceDesc(name)
                              .orElseThrow( ()-> new RuntimeException("No Items Found"));

    }


    @Override
    public List<Items> sortItemsByPriceLowToHigh(String name) {

        return itemsRepository.findAllByNameContainsOrderByPriceAsc(name)
                                        .orElseThrow( ()->new RuntimeException("No Items found") );

    }


}

