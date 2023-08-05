package org.example.domain.interfaces;


public interface AggregateRoot<ID extends DomainId> extends DomainEntity<ID> {

    boolean sameAs(Object object);
}
