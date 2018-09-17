Codeforces Round #465 (Div. 2) URL : http://codeforces.com/contest/935/problem/C

Фифа и Фафа

  Фифа и Фафа живут в одной квартире. Фифа любит компьютерные игры и хочет скачать одну новую игру про футбол. 
К сожалению, Фафа уже израсходовал всю их месячную квоту интернета, однако, Фифа может подключиться к интернету через 
свою Wi-Fi точку доступа. К точке доступа можно подключиться в некотором радиусе r метров от места ее установки, Фифа 
может выбирать этот радиус. Фифа должен поставить точку доступа внутри квартиры, которая имеет круглую форму радиуса R. 
Фифа хочет минимизировать площади квартиры, из которой нельзя подключиться к точке доступа, при этом он не хочет, чтобы Фафа 
или кто-либо снаружи квартиры смог подключиться к точке доступа.
  Мир можно представить как бесконечную плоскость. Центр квартиры расположен в точке (x1, y1), квартира имеет радиус R, 
ноутбук Фафы расположен в точке (x2, y2), не обязательно внутри квартиры. Можно представлять ноутбук Фафы как точку. 
Найдите точку установки и радиус, которые должен выбрать Фифа для своей точки доступа, чтобы минимизировать 
непокрытую площадь квартиры.

Входные данные
Единственная строка содержит пять целых чисел R, x1, y1, x2, y2 (1 ≤ R ≤ 105, |x1|, |y1|, |x2|, |y2| ≤ 105).

Выходные данные
Выведите три числа xap, yap, r где (xap, yap) — точка, в которую Фифа должен поставить точку доступа, 
а r — ее радиус действия.

Примеры
входные данные
5 3 3 1 1
выходные данные
3.7677669529663684 3.7677669529663684 3.914213562373095

входные данные
10 5 5 5 15
выходные данные
5.0 5.0 10.0