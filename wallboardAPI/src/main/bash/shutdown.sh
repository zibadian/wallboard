PORT=8080
SECURITY_CODE=$(cat .securitycode)
URL="http://localhost:${PORT}/application/stop/${SECURITY_CODE}"
wget -qS $URL -O /dev/null