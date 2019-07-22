using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text.RegularExpressions;

namespace YandexCval
{
    struct struct_forE
    {
        public List<string> tryExceptions, exceptions;

        public struct_forE(List<string> tryExceptions, List<string> exceptions)
        {
            this.tryExceptions = tryExceptions;
            this.exceptions = exceptions;
        }
    }

    class Program
    {
        public static void A()
        {
            int[] data = Array.ConvertAll(Console.ReadLine().Split(' '), Int32.Parse);
            List<int> times = Array.ConvertAll(Console.ReadLine().Split(' '), Int32.Parse).Distinct().ToList();
            int count_time = 0;

            while (true)
            {
                int i = times.IndexOf(count_time);
                if (i != -1)
                {
                    data[2]--;
                    if (data[2] == 0) break;
                    times[i] += data[1];
                    times = times.Distinct().ToList();
                }
                count_time++;
            }
            Console.WriteLine(count_time);
        }

        public static void B()
        {
            int countGames = Int32.Parse(Console.ReadLine());
            Dictionary<string, int> playersInfo = new Dictionary<string, int>();
            for (int i = 0; i < countGames; i++)
            {
                string[] data = Console.ReadLine().Split(' ');

                if (playersInfo.ContainsKey(data[0]))
                    playersInfo[data[0]]++;
                else
                    playersInfo.Add(data[0], 1);

                if (playersInfo.ContainsKey(data[1]))
                    playersInfo[data[1]]++;
                else
                    playersInfo.Add(data[1], 1);
            }

            if (playersInfo.Keys.Count != countGames + 1)
                Console.WriteLine("NO SOLUTION");
            else
            {
                double countMatches = Math.Log(countGames + 1, 2);
                List<string> winners = new List<string>();
                foreach (string key in playersInfo.Keys)
                {
                    if (playersInfo[key] == countMatches)
                        winners.Add(key);
                }
                if (winners.Count > 2)
                    Console.WriteLine("NO SOLUTION");
                else
                    Console.WriteLine(winners[0] + " " + winners[0]);
            }
        }

        public static void C()
        {
            string[] data = Console.ReadLine().Split(' ');
            short point = Int16.Parse(data[0]);
            short[] cards = new short[Int32.Parse(data[1])];
            cards = Array.ConvertAll(Console.ReadLine().Split(' '), Int16.Parse);

            short pointVasya = 0, pointPetya = 0;
            for (int i = 0; i < cards.Length; i++)
            {
                if (cards[i] % 5 == 0 & cards[i] % 3 != 0) pointVasya++;
                if (cards[i] % 3 == 0 & cards[i] % 5 != 0) pointPetya++;
                if (pointVasya == point) { Console.WriteLine("Vasya"); return; }
                if (pointPetya == point) { Console.WriteLine("Petya"); return; }
            }
            if (pointPetya == pointVasya)
                Console.WriteLine("Draw");
            else if (pointPetya > pointVasya)
                Console.WriteLine("Petya");
            else
                Console.WriteLine("Vasya");
        }
        
        public static void D()
        {
            string str = Console.ReadLine();
            Regex regex = new Regex(@"~[\d|a-f][a-f|\d]", RegexOptions.IgnoreCase);
            int countChange = 0;

            MatchCollection matches = regex.Matches(str);
            while (matches.Count > 0)
            {
                countChange++;
                foreach (Match match in matches)
                {
                    byte X = 0, Y = 0;
                    if (match.Value[1] >= '0' && match.Value[1] <= '9')
                        X = (byte)((byte)match.Value[1] - 48);
                    else
                        X = (byte)((byte)match.Value[1] - 87);

                    if (match.Value[2] >= '0' && match.Value[2] <= '9')
                        Y = (byte)((byte)match.Value[2] - 48);
                    else
                        Y = (byte)((byte)match.Value[2] - 87);

                    Regex localRegex = new Regex(match.Value);
                    str = localRegex.Replace(str, Convert.ToChar(16 * X + Y).ToString());
                }
                matches = regex.Matches(str);
            }
            Console.WriteLine(countChange);
        }

        public static void E()
        {
            short x = Convert.ToInt16(Console.ReadLine());
            short n = Convert.ToInt16(Console.ReadLine());
            Dictionary<string, struct_forE> FuncAndThrows = new Dictionary<string, struct_forE>();
            using (StreamReader sr = new StreamReader("code.txt"))
            {
                string line = null, currentFunc = null;
                Regex regex = new Regex(@"\s+");
                bool TryBlock = false;
                while((line = sr.ReadLine()) != null)
                {
                    line = line.Trim();
                    line = regex.Replace(line, " ");

                    if (line.Contains("func ")) {
                        currentFunc = line.Split(' ')[1];
                        currentFunc = currentFunc.Substring(0, currentFunc.Length - 2);
                        FuncAndThrows[currentFunc] = new struct_forE(new List<string>(), new List<string>());
                    }

                    if (line.Contains("maybethrow") && TryBlock == false)
                        FuncAndThrows[currentFunc].exceptions.Add(line.Split(' ')[1]);
                    if (line.Contains("maybethrow") && TryBlock == true)
                        FuncAndThrows[currentFunc].tryExceptions.Add(line.Split(' ')[1]);
                    foreach (string key in FuncAndThrows.Keys)
                    {
                        if (line.Contains(key) && TryBlock == false)
                            foreach (string exception in FuncAndThrows[key].exceptions)
                                FuncAndThrows[currentFunc].exceptions.Add(exception);
                        else if (line.Contains(key) && TryBlock == true)
                            foreach (string exception in FuncAndThrows[key].exceptions)
                                FuncAndThrows[currentFunc].tryExceptions.Add(exception);
                    }

                    if (line.Contains("try"))
                        TryBlock = true;

                    if (line.Contains("suppress"))
                    {
                        TryBlock = false;
                        int startExc = line.IndexOf("suppress") + 9;
                        string[] exceptions = line.Substring(startExc).Split(',');
                        foreach (string exception in exceptions)
                            FuncAndThrows[currentFunc].tryExceptions.Remove(exception.Trim());
                        foreach (string exception in FuncAndThrows[currentFunc].tryExceptions)
                            FuncAndThrows[currentFunc].exceptions.Add(exception);
                    }
                }
            }

            foreach (string key in FuncAndThrows.Keys)
            {
                Console.WriteLine(key + ":");
                FuncAndThrows[key].exceptions.Sort();
                if (FuncAndThrows[key].exceptions.Count > x)
                    Console.WriteLine(x);
                else
                    Console.WriteLine(FuncAndThrows[key].exceptions.Count);

                for (int i = 0; i < x; i++)
                    Console.WriteLine(FuncAndThrows[key].exceptions[i]);
            }
        }

        public static void F()
        {
            int numberBadCommit = 8;
            int countCommits = Convert.ToInt32(Console.ReadLine());
            int countRequest = 0, leftValue = 0;
            List<int> request = new List<int>();
            List<int> response = new List<int>();
            while (countRequest < 25)
            {
                if (countCommits - leftValue == 2)
                {
                    request.Add(leftValue + 1);
                    if (leftValue + 1 < numberBadCommit)
                        response.Add(1);
                    else
                    {
                        response.Add(0);
                        break;
                    }

                    request.Add(leftValue + 2);
                    response.Add(0);
                    break;
                }

                int valueRequest = (countCommits - leftValue) / 2;
                valueRequest += leftValue;
                request.Add(valueRequest);
                if (valueRequest >= numberBadCommit)
                {
                    response.Add(0);
                    countCommits = valueRequest;
                }
                else
                {
                    response.Add(1);
                    leftValue = valueRequest;
                }
                countRequest++;
            }

            foreach (int value in response)
                Console.WriteLine(value);
            foreach (int value in request)
                Console.WriteLine(value);
            Console.WriteLine("! " + request[request.Count - 1]);
        }

        static void Main(string[] args)
        {
            Console.ReadKey();
        }
    }
}
