Codeforces Round #321 (Div. 2) URL : http://codeforces.com/problemset/problem/580/C

Кефа и парк

Кефа решил отпраздновать свой первый крупный заработок походом в ресторан.
Он живет возле необычного парка. Парк представляет из себя подвешенное дерево из n вершин c корнем в вершине 1. 
В вершине 1 также находится дом Кефы. К сожалению для нашего героя, в парке также находятся коты. 
Кефа уже выяснил номера вершин, в которых находятся коты.
В листовых вершинах парка находятся рестораны. Кефа хочет выбрать ресторан, в который он пойдет, но, к сожалению, он 
очень боится котов, поэтому он ни за что не пойдёт в ресторан, на пути к которому от его дома найдётся более m подряд 
идущих вершин с котами.

Ваша задача — помочь Кефе посчитать количество ресторанов, в которые он может сходить.

Входные данные
В первой строке записаны два целых числа n и m (2 ≤ n ≤ 105, 1 ≤ m ≤ n) — количество вершин дерева и максимальное количество 
подряд идущих вершин с котами, которое способен перенести Кефа.
Во второй строке содержится n целых чисел a1, a2, ..., an, где каждое ai либо равняется 0 (тогда в вершине i нет кота), 
либо равняется 1 (тогда в вершине i есть кот).
В следующих n - 1 строках записаны ребра дерева в формате «xi yi»(без кавычек) (1 ≤ xi, yi ≤ n, xi ≠ yi), 
где xi и yi — вершины дерева, соединенные очередным ребром.
Гарантируется, что данный набор рёбер задаёт дерево.

Выходные данные
Одно целое число — количество различных листьев дерева, на пути от дома Кефы до которых не больше m подряд идущих вершин 
с котами.

Примеры
входные данные
4 1
1 1 0 0
1 2
1 3
1 4
выходные данные
2

входные данные
7 1
1 0 1 1 0 0 0
1 2
1 3
2 4
2 5
3 6
3 7
выходные данные
2
