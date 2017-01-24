package com.hlorka.domain;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by lbilenki on 1/23/2017.
 */
public class Team extends ObjectWithId {
    private final String name;
    private final int teamSize;
    private final Set<User> members = new LinkedHashSet<>();

    public Team(int id, String name, int teamSize) {
        super(id);
        this.name = name;
        this.teamSize = teamSize;
    }

    public String getName() {
        return name;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public Set<User> getMembers() {
        return Collections.unmodifiableSet(members);
    }

    public boolean isMember(User user) {
        return members.contains(user);
    }

    public boolean isFull() {
        return members.size() == teamSize;
    }

    public boolean addMember(User user) {
        if (isFull()) {
            throw new IllegalStateException(String.format("Team %s is full. teamSize=%d", name, members.size()));
        }

        return members.add(user);
    }

    public boolean removeMember(User user) {
        return members.remove(user);
    }

    @Override
    public String toString() {
        return String.format("Team{name='%s', teamSize=%d, members=%s}", name, teamSize, members);
    }
}
