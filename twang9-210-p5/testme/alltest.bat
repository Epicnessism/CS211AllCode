for /L %%x in (1,1,13) do (
	echo TESTING %%x;
	java -cp .;junit-cs211.jar;imp%%x Project5Tests
)