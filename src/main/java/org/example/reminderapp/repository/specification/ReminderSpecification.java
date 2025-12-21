package org.example.reminderapp.repository.specification;

import jakarta.persistence.criteria.Predicate;
import org.example.reminderapp.dto.ReminderFilterDto;
import org.example.reminderapp.entity.Reminder;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

// Class for hard filtres

public class ReminderSpecification {

    public static Specification<Reminder> withFilters(ReminderFilterDto filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filters by name
            if (filter.getTitle() != null && !filter.getTitle().isBlank()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),
                        "%" + filter.getTitle().toLowerCase() + "%"));
            }

            // Filters by description
            if (filter.getDescription() != null && !filter.getDescription().isBlank()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")),
                        "%" + filter.getDescription().toLowerCase() + "%"));
            }

            // Filters by date "from"
            if (filter.getToDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("remindAt"), filter.getToDate()));
            }

            // Filters by date "before"
            if (filter.getUserId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("userId"), filter.getUserId()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
