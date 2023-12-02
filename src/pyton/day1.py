file = open("inputFiles/Day1.txt", "r")

data = []
for line in file:
    data.append(line.strip("\n"))

def part1(data):
    total = 0
    for line in data:
        digits = []
        for char in line: 
            if char.isdigit():
                digits.append(char)
        total += (int) (digits[0] +  digits[-1])
    return total 

def part2(data):
    total = 0
    for line in data:
        digits = []
        for char in line: 
            if char.isdigit():
                digits.append(char)
        total += (int) (digits[0] +  digits[-1])
    return total 

print("Part 1 answer", part1(data))
print("Part 2 answer", part2(data))