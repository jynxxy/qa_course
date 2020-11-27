package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<UserData> {

    private Set<UserData> delegateOb;

    public Users(Collection<UserData> users) {
        this.delegateOb = new HashSet<>(users);
    }

    @Override
    protected Set delegate() {
        return delegateOb;
    }

}
