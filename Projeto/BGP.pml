chan c01 = [1] of {byte,byte}; 
chan c02 = [1] of {byte,byte}; 
chan c12 = [1] of {byte,byte}; 
chan c21 = [1] of {byte,byte}; 
active proctype n0() { 
c01 ! -1, 0; 
c02 ! 0, 0; 
}
active proctype n1() { 
byte electNode;
byte x;
byte path;
byte paths[3]; 
byte costs[3]; 
costs[0] = 1; 
costs[1] = 1; 
costs[2] = 1; 
byte min;
do
 	:: c01 ? x, path; 
 		costs[0] = x;
 			if
 				:: costs[electNode] > costs[0] -> electNode = 0;
 					path = 1 * 10; 
 					if
 						:: c12 ! costs[electNode], path 
 					fi
 			fi
 	:: c21 ? x, path; 
 	if
 		:: path == 210 -> 
if
 			:: electNode == 1 -> 
 			x = 1;
 			min = costs[0];
 			do
 				:: x >= 3 -> break 
 				:: costs[x] < min && x!=1 -> min = costs[x]; electNode = x; x = x+1 
 				:: x = x+1
 			od
 		fi
 		if
 			:: electNode != 1 -> path = paths[electNode]; 
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 1 * 10^x + path; 
 			if
 			:: c12 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == 0 -> costs[2] = 1; 
 			if
 				:: costs[electNode] > costs[2] -> electNode = 2; 
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 1 * 10^x + path 
 					if
 						:: c12 ! costs[electNode], path; 
 					fi
 			fi
 		fi
 	fi
 	if
 		:: path == 210 -> 
if
 			:: electNode == 1 -> 
 			x = 1;
 			min = costs[0];
 			do
 				:: x >= 3 -> break 
 				:: costs[x] < min && x!=1 -> min = costs[x]; electNode = x; x = x+1 
 				:: x = x+1
 			od
 		fi
 		if
 			:: electNode != 1 -> path = paths[electNode]; 
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 1 * 10^x + path; 
 			if
 			:: c12 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == -1 -> costs[2] = 0; 
 			if
 				:: costs[electNode] > costs[2] -> electNode = 2; 
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 1 * 10^x + path 
 					if
 						:: c12 ! costs[electNode], path; 
 					fi
 			fi
 		fi
 	fi
 	if
 		:: path == 210 -> 
if
 			:: electNode == 1 -> 
 			x = 1;
 			min = costs[0];
 			do
 				:: x >= 3 -> break 
 				:: costs[x] < min && x!=1 -> min = costs[x]; electNode = x; x = x+1 
 				:: x = x+1
 			od
 		fi
 		if
 			:: electNode != 1 -> path = paths[electNode]; 
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 1 * 10^x + path; 
 			if
 			:: c12 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == 1 -> costs[2] = -1; 
 			if
 				:: costs[electNode] > costs[2] -> electNode = 2; 
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 1 * 10^x + path 
 					if
 						:: c12 ! costs[electNode], path; 
 					fi
 			fi
 		fi
 	fi
od
}
active proctype n2() { 
byte electNode;
byte x;
byte path;
byte paths[3]; 
byte costs[3]; 
costs[0] = 1; 
costs[1] = 1; 
costs[2] = 1; 
byte min;
do
 	:: c02 ? x, path; 
 		costs[0] = x;
 			if
 				:: costs[electNode] > costs[0] -> electNode = 0;
 					path = 2 * 10; 
 					if
 						:: c21 ! costs[electNode], path 
 					fi
 			fi
 	:: c12 ? x, path; 
 	if
 		:: path == 120 -> 
if
 			:: electNode == 2 -> 
 			x = 1;
 			min = costs[0];
 			do
 				:: x >= 3 -> break 
 				:: costs[x] < min && x!=2 -> min = costs[x]; electNode = x; x = x+1 
 				:: x = x+1
 			od
 		fi
 		if
 			:: electNode != 2 -> path = paths[electNode]; 
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 2 * 10^x + path; 
 			if
 			:: c21 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == 0 -> costs[1] = 1; 
 			if
 				:: costs[electNode] > costs[1] -> electNode = 1; 
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 2 * 10^x + path 
 					if
 						:: c21 ! costs[electNode], path; 
 					fi
 			fi
 		fi
 	fi
 	if
 		:: path == 120 -> 
if
 			:: electNode == 2 -> 
 			x = 1;
 			min = costs[0];
 			do
 				:: x >= 3 -> break 
 				:: costs[x] < min && x!=2 -> min = costs[x]; electNode = x; x = x+1 
 				:: x = x+1
 			od
 		fi
 		if
 			:: electNode != 2 -> path = paths[electNode]; 
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 2 * 10^x + path; 
 			if
 			:: c21 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == -1 -> costs[1] = 0; 
 			if
 				:: costs[electNode] > costs[1] -> electNode = 1; 
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 2 * 10^x + path 
 					if
 						:: c21 ! costs[electNode], path; 
 					fi
 			fi
 		fi
 	fi
 	if
 		:: path == 120 -> 
if
 			:: electNode == 2 -> 
 			x = 1;
 			min = costs[0];
 			do
 				:: x >= 3 -> break 
 				:: costs[x] < min && x!=2 -> min = costs[x]; electNode = x; x = x+1 
 				:: x = x+1
 			od
 		fi
 		if
 			:: electNode != 2 -> path = paths[electNode]; 
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 2 * 10^x + path; 
 			if
 			:: c21 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == 1 -> costs[1] = -1; 
 			if
 				:: costs[electNode] > costs[1] -> electNode = 1; 
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 2 * 10^x + path 
 					if
 						:: c21 ! costs[electNode], path; 
 					fi
 			fi
 		fi
 	fi
od
}
ltl { <> [] ( empty(c01) /\ empty(c02) /\ empty(c12) /\ empty(c21) ) }