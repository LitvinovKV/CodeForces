Codeforces Round #461 (Div. 2) URL : http://codeforces.com/problemset/problem/922/E

Птички

Помимо плюшевых игрушек, Имп также обожает маленьких желтых птичек!
Для призыва птичек требуется очень сильное колдунство. На аллее в парке в ряд расположены n деревьев, на каждом 
из которых находится по одному птичьему гнезду. В i-м гнезде живет ci птичек; чтобы призвать одну птичку из этого гнезда, 
нужно стоять под этим деревом и потратить costi единиц маны. Однако, за каждую призванную птичку Имп поднимает свой 
максимальный уровень маны на B единиц. Имп призывает птиц по одной, из i-го гнезда он может призвать от 0 до ci птиц.

Изначально Имп находится у первого дерева и имеет запас маны, равный W, его максимальный уровень маны тоже равен W. 
Имп может двигаться только вперед, при перемещении между соседними деревьями уровень маны Импа восстанавливается на X 
(но не может превысить максимальное значение). Двигаясь исключительно вперед, каково максимальное количество птичек, 
которое может призвать Имп?

Входные данные
  - В первой строчке заданы четыре числа n, W, B, X (1 ≤ n ≤ 103, 0 ≤ W, B, X ≤ 109) — число деревьев, начальный запас маны, 
бонус к максимальному количеству маны за призыв одной птички и количество восполняемой при передвижении маны, соответственно.
  - Во второй строке заданы n чисел c1, c2, ..., cn (0 ≤ ci ≤ 104), где ci — количество птичек, обитающих в i-м гнезде. 
Гарантируется, что .
  - В третьей строке заданы n чисел cost1, cost2, ..., costn (0 ≤ costi ≤ 109), где costi — стоимость призыва одной птички 
из i-го гнезда.

Выходные данные
Выведите одно число — максимальное количество птичек, которое можно призвать.

Примеры:
входные данные
2 12 0 4
3 4
4 2
выходные данные
6

входные данные
4 1000 10 35
1 2 4 5
1000 500 250 200
выходные данные
5

входные данные
2 10 7 11
2 10
6 1
выходные данные
11