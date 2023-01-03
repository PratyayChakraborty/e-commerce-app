package com.ecom.Service;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.Model.Cart;
import com.ecom.Model.ItemQuantity;
import com.ecom.Model.Items;
import com.ecom.Model.UserModel;
import com.ecom.Repository.ItemsRepository;
import com.ecom.Repository.UserEntityRepository;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private ManageUserService userService;

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public Cart addItemToCart( Integer itemId ) {

        UserModel user = userService.getUser();

        Items items = itemsRepository.findById( itemId ).orElseThrow( () -> new RuntimeException("Item not found") );

        if( user.getCart()==null ) user.setCart( new Cart() );
        Cart cart = user.getCart();

        if( cart.getItems()==null ) cart.setItems( new ArrayList<>(10) );

        List<ItemQuantity> ls =  cart.getItems().stream()
                                                .filter(i -> i.getItem().getId().equals( itemId ) )
                                                .toList() ;

        if( ls.isEmpty() ){

            ItemQuantity iq = new ItemQuantity();

            iq.setItem( items );
            iq.setQuantity( 1 );

            cart.getItems().add( iq );

        }
        else ls.get(0).setQuantity( ls.get(0).getQuantity()+1 );

        double Totalprice = 0;
        for( ItemQuantity i:cart.getItems()){
            Totalprice += i.getQuantity() * i.getItem().getPrice();
        }

        cart.setTotalPrice( Totalprice );

        userEntityRepository.save( user );

        return cart;
    }

    @Override
    public Cart getCartInfo() {

        UserModel model = userService.getUser();

        if( model.getCart()==null ) throw new RuntimeException( "You don't have any time in your cart" );

        return model.getCart();
    }

    @Override
    public Cart removeItemFromCart( Integer id ) {

        UserModel model = userService.getUser();

        Cart c = model.getCart();
        if( c==null || c.getItems().isEmpty() ) throw new RuntimeException("Cart is empty");

        List<ItemQuantity> iq = c.getItems();

        ItemQuantity sq = iq.stream().filter( i-> Objects.equals( i.getItem().getId(), id ))
                .findFirst()
                .orElseThrow( ()-> new RuntimeException("No Item found in cart") );

        iq.removeIf(item -> Objects.equals(item.getItem().getId(), id ) );

        double Totalprice = 0;
        for( ItemQuantity i:c.getItems()){
            Totalprice += i.getQuantity() * i.getItem().getPrice();
        }


        c.setTotalPrice( Totalprice );
        userEntityRepository.save( model );

        return c;
    }

    @Override
    public Double totalCartAmount() {

        UserModel  model = userService.getUser();

        return model.getCart().getTotalPrice();

    }

    // increment  order quantity
    @Override
    public Cart increaseQuantity(Integer itemId){

        UserModel  model = userService.getUser();

        Cart c = model.getCart();
        if( c==null || c.getItems().isEmpty() ) throw new RuntimeException("Cart is empty");

        Cart cart  =   model.getCart();

        List<ItemQuantity> itemsquantity =  cart.getItems();

        for(ItemQuantity item : itemsquantity){

            if(item.getItem().getId() == itemId){

                item.setQuantity(item.getQuantity()+1);
            }

        }

        double Totalprice = 0;
        for( ItemQuantity i:cart.getItems()){
            Totalprice += i.getQuantity() * i.getItem().getPrice();
        }

        c.setTotalPrice( Totalprice );
        userEntityRepository.save( model );

        return c;

    }

    @Override
    public Cart decreaseQuantity(Integer itemId){

        UserModel model = userService.getUser();

        Cart c = model.getCart();
        if( c==null || c.getItems().isEmpty() ) throw new RuntimeException("Cart is empty");

        List<ItemQuantity> iq = c.getItems();

        ItemQuantity sq = iq.stream().filter( i-> Objects.equals( i.getItem().getId(), itemId ))
                .findFirst()
                .orElseThrow( ()-> new RuntimeException("No Item found in cart") );

        if(sq.getQuantity()<=1)
            iq.removeIf(item -> Objects.equals(item.getItem().getId(), itemId));

        else sq.setQuantity( sq.getQuantity()-1 );

        double Totalprice = 0;

        for( ItemQuantity i:c.getItems()){
            Totalprice += i.getQuantity() * i.getItem().getPrice();
        }

        c.setTotalPrice( Totalprice );
        userEntityRepository.save( model );

        return c;

    }

}
