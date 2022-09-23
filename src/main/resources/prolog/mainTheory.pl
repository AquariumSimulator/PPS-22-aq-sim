son(X, Y) :- parent(Y, X).
grandparent(X, Y) :- parent(X, Z), parent(Z, Y).
grandson(X, Y) :- grandparent(Y, X).