TString& operator + (const TString& l, const TString& r);

TString& :: TString operator + (const TString& l, const TString& r)
{
  TString temp;
  temp.data_ = l.concat(r);
  return (temp);
}
