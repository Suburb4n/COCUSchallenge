package org.example.domain.interfaces;

public interface DomainEntity<ID extends DomainId> {
    public ID identity();  //Id identificador de entidade
    public boolean sameAs(Object object); //verificar se os objetos são iguais

}
