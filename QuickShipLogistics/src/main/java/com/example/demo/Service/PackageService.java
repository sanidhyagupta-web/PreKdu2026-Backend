package com.example.demo.Service;

import com.example.demo.Models.Package;
import com.example.demo.Models.PackageDto;
import com.example.demo.Utilities.Conversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PackageService {

    // CopyOnWriteArrayList is thread safe , so it is apt to be used over here.
    List<Package> packageList = new CopyOnWriteArrayList<>();

    @Autowired
    private Conversion conversion ;

    private final int cores = Runtime.getRuntime().availableProcessors();
    private ExecutorService executorService = Executors.newFixedThreadPool(cores);
    Set<String> handleIdempotency = new HashSet<>();

    public Package addPackage(PackageDto pack){

        Package package1 = conversion.convert(pack);

        if (handleIdempotency.contains(package1.getId())) handleIdempotency.add(package1.getId());
        else return package1;

        packageList.add(package1);
        executorService.submit(()->addStatus(package1));
        return package1;
    }

    private synchronized void addStatus(Package p) {
        try {
            Thread.sleep(3000);
            p.setStatus("SORTED");
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    public Double getRevenue() {
        AtomicReference<Double> revenue = new AtomicReference<>(0.0);
        packageList.stream().filter(x-> Objects.equals(x.getStatus(), "SORTED"))
                .forEach(x->{
                    Double v1 = revenue.updateAndGet(v ->  (( (x.getWeight() * 2.5))));
                });
        return revenue.get();
    }
}
