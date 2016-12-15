/*
 * Copyright 2016 Addepar. All rights reserved.
 */
package boj.solution.groupcontacts;

import java.util.*;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
final class GroupContacts {

  Set<Set<Contact>> solve(List<Contact> contacts) {
    Map<String, List<Contact>> e2c = new HashMap<>();
    for (Contact contact : contacts) {
      for (String email : contact.emails) {
        if (!e2c.containsKey(email)) {
          e2c.put(email, new ArrayList<>());
        }
        e2c.get(email).add(contact);
      }
    }
    Set<Set<Contact>> res = new HashSet<>();
    Set<Contact> vis = new HashSet<>();
    for (Contact contact : contacts) {
      if (!vis.contains(contact)) {
        Set<Contact> tmp = new HashSet<>();
        dfs(contact, vis, e2c, tmp);
        res.add(tmp);
      }
    }
    return res;
  }

  private void dfs(Contact contact, Set<Contact> vis, Map<String, List<Contact>> e2c, Set<Contact> tmp) {
    if (vis.contains(contact)) {
      return;
    }
    vis.add(contact);
    tmp.add(contact);
    for (String email : contact.emails) {
      for (Contact next : e2c.get(email)) {
        dfs(next, vis, e2c, tmp);
      }
    }
  }

  static class Contact {
    private final String name;
    private final List<String> emails;

    public Contact(String name, List<String> emails) {
      this.name = name;
      this.emails = emails;
    }
  }

}
