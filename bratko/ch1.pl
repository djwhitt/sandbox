% -*- mode: Prolog; -*-

% 1st generation
parent(pam, bob).               % Pam is a parent of Bob
parent(tom, bob).
parent(tom, liz).

% 2nd generation
parent(bob, ann).
parent(bob, pat).

% 3rd generation
parent(pat, jim).

female(pam).                    % Pam is a female
female(liz).
female(pat).
female(ann).

male(tom).
male(bob).
male(jim).

offspring(Y, X) :-
  parent(X, Y).

mother(X, Y) :-
  parent(X, Y),
  female(Y).

grandparent(X, Z) :-
  parent(X, Y),
  parent(Y, Z).

sister(X, Y) :-
  parent(Z, X),
  parent(Z, Y),
  female(X).
  % different(X, Y)

predecessor(X, Z) :-
  parent(X, Z).

predecessor(X, Z) :-
  parent(X, Y),
  predecessor(Y, Z).
