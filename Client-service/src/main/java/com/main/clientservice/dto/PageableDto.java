package com.main.clientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageableDto implements Pageable {

    private int page;
    private int size;
    private String sort;

    @Override
    public int getPageNumber() {
        return this.page;
    }

    @Override
    public int getPageSize() {
        return this.size;
    }

    @Override
    public long getOffset() {
        return (long) this.page * this.size;
    }

    @Override
    public Sort getSort() {
        if (sort != null && !sort.isEmpty()) {
            String[] sortArr = sort.split(",");
            List<Sort.Order> orders = new ArrayList<>();
            for (String sortStr : sortArr) {
                String[] sortProp = sortStr.split(":");
                orders.add(new Sort.Order(Sort.Direction.fromString(sortProp[1]), sortProp[0]));
            }
            return Sort.by(orders);
        }
        return Sort.unsorted();
    }

    @Override
    public Pageable next() {
        return null; // You can implement logic here if needed
    }

    @Override
    public Pageable previousOrFirst() {
        return null; // You can implement logic here if needed
    }

    @Override
    public Pageable first() {
        return null; // You can implement logic here if needed
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return this.page > 0;
    }
}

