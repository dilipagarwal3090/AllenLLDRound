package com.allen;

import com.allen.enums.ItemStatus;
import com.allen.interfaces.ClaimService;
import com.allen.interfaces.DealService;
import com.allen.interfaces.InventoryService;
import com.allen.interfaces.TimeService;
import com.allen.interfaces.UserService;
import com.allen.model.Item;
import com.allen.repository.ClaimRepositoryImpls;
import com.allen.repository.DealRepositoryImpl;
import com.allen.repository.InventoryRepositoryImpl;
import com.allen.repository.UserRepositoryImpl;
import com.allen.repository.interfaces.ClaimRepository;
import com.allen.repository.interfaces.DealRepository;
import com.allen.repository.interfaces.InventoryRepository;
import com.allen.repository.interfaces.UserRepository;
import com.allen.service.ClaimServiceImpl;
import com.allen.service.DealServiceImpl;
import com.allen.service.InventoryServiceImpl;
import com.allen.service.TimeServiceImpl;
import com.allen.service.UserServiceImpl;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        DealRepository dealRepository = new DealRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();
        ClaimRepository claimRepository = new ClaimRepositoryImpls();
        InventoryRepository inventoryRepository = new InventoryRepositoryImpl();

        DealService dealService = new DealServiceImpl(dealRepository);
        UserService userService = new UserServiceImpl(userRepository);
        ClaimService claimService = new ClaimServiceImpl(claimRepository, new ConcurrencyManager());
        TimeService timeService = new TimeServiceImpl();
        InventoryService inventoryService = new InventoryServiceImpl(inventoryRepository);

        // 1 hour from now
        dealService.createDeal(50.0, 10, new Date(System.currentTimeMillis() * 3600000));

        userService.registerUser("user1");

        claimService.canClaimDeal(1, "user1");

        // check if deal can be claimed
        System.out.println(dealRepository.findById(1));
        boolean isExpired = timeService.isDealExpired(dealRepository.findById(1).getEndTime());
        System.out.println("Is deal expired  " + isExpired);

        dealRepository.findById(1).setEndTime(new Date(0L));
        isExpired = timeService.isDealExpired(dealRepository.findById(1).getEndTime());
        System.out.println("Is deal expired  " + isExpired);

        Item item = Item.builder().name("Item1").price(100.0).quantity(5).status(ItemStatus.AVAILABLE).build();
        inventoryService.addItemToInventory(item);

        //Check if item is available
        boolean isAvailable = inventoryService.isItemAvailable(1);
        System.out.println("Is item available before sold  " + isAvailable);

        //mark an item as sold
        inventoryService.markItemAsSold(1);
        claimService.claimDeal(1, "user1");
        boolean canClaimDeal = claimService.canClaimDeal(1, "user1");
        System.out.println("Can claim deal " + canClaimDeal);
        //
        //        //check if item is available after sold
        //        isAvailable = inventoryService.isItemAvailable(1);
        //        System.out.println("Is item available after sold " + isAvailable);
        //
        //        // Example for concurrency
        //        int numUsers = 5;
        //        ExecutorService executorService = Executors.newFixedThreadPool(numUsers);
        //        for (int i = 0; i < numUsers; i++) {
        //            String username = "user" + i;
        //            userService.registerUser(username);
        //        }
        //
        //        // attempt to claim the deal
        //        for (int i = 0; i <= 5; i++) {
        //            int userId = i;
        //            executorService.execute(() -> {
        //                String username = "user" + userId;
        //                try {
        //                    claimService.claimDeal(1, username);
        //                    boolean canClaimDeal = claimService.canClaimDeal(1, username);
        //                    System.out.println("Claimed deal by user" + username);
        //                } catch (RuntimeException ex) {
        //                    System.out.println("user not able to  claim deal" + username);
        //                }
        //            });
        //        }
        //        executorService.shutdown();
    }
}