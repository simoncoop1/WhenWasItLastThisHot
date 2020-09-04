import csv

f = 'cetmaxdly1878on_urbadj4.dat'

out = []

with open(f, newline='') as csvfile:
                areader = csv.reader(csvfile, delimiter=' ',doublequote=False)
                for row in areader:
                    newRow = []
                    for a in row:
                        if a is not '':
                            newRow.append(a)
                    out.append(newRow)

print(out)

#check all rows are of same length
length = len(out[0])
result = "Length Test Pass"
for a in out:
    if length is not len(a):
        result = "Length Test Fail"

print(result)



