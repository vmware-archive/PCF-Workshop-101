## This only needs to be ran on an "initial" install of PCF.  The environment can be seeded/cleanedup over and over after this iniial run.
## Create Admin User
## needs two parameters passed in - pcfdomain and "UAA" Admin Client Credentials.
##
## Example:
## $ createinstructor.sh pcf5.cloud.fe.pivotal.io p349dkdm4f83j8d90

gem install cf-uaac
uaac target uaa.$1
uaac token client get admin -s $2
uaac user add instructor -p nativecloud --emails workshop@pivotal.io

uaac member add cloud_controller.admin instructor
uaac member add uaa.admin instructor
uaac member add scim.read instructor
uaac member add scim.write instructor
